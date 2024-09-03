using namespace System.IO
using namespace System.Text

cd $PSScriptRoot
$ErrorActionPreference = 'Stop'

$outName = "all.csv"
$resourceSubpath = [Path]::DirectorySeparatorChar + [string]::Join([Path]::DirectorySeparatorChar,@('main','resources','MIME'))


$resourceDir = Get-Item $('..' + $resourceSubpath)

$outPath = Join-Path -Path $resourceDir.FullName -ChildPath $outName

if([File]::Exists($outPath)){
    [File]::Delete($outPath)
}


$result = @{}
$inItems = $resourceDir | Get-ChildItem -Filter "*.csv"
foreach($inItem in $inItems){
    $table = @(Import-Csv -Path $inItem.FullName | Select-Object -Property Name, Template)
    foreach($row in $table){
        $result[$row.Name] = $row
    }
}

$result = $result.Values | Sort-Object -Property Name

[string[]]$outLines = $result | Select-Object -ExpandProperty Template -Unique | Sort-Object
$outString = [string]::Join("`n", $outLines)
[File]::WriteAllText('..\main\resources\http\mime-types.txt', $outString, [Encoding]::UTF8);  

$outString = [string]::Join("`n", @($result | ConvertTo-Csv -Delimiter ',' -NoTypeInformation | %{$_.Replace('"','')}))
[File]::WriteAllText($outPath, $outString, [Encoding]::UTF8)
