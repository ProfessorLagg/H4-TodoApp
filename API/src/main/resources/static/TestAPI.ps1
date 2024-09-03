cls
cd $PSScriptRoot
$ErrorActionPreference = 'Stop'

$url = 'http://localhost:8080/api/todoitem'

#[string]$csvContent = [System.IO.File]::ReadAllText()
#$todoItems = ConvertFrom-Csv -Delimiter ';' -InputObject $csvContent

$todoItems = Import-Csv -Path "$PSScriptRoot\TodoItems.csv" -Delimiter ';' | Select-Object title, description,`
    @{Name="deadline";Expression={[DateTime]::Parse($_.deadline).ToString("yyyy-MM-ddTHH\:mm\:ss.fffffffzzz", [System.Globalization.CultureInfo]::InvariantCulture);}},`
    @{Name="completed";Expression={[bool]::Parse($_.completed)}},`
    @{Name="duration";Expression={[int]::Parse($_.duration)}},
    @{Name="importance";Expression={[int]::Parse($_.importance)}},
    @{Name="hatelevel";Expression={[int]::Parse($_.hatelevel)}}

foreach($todoItem in $todoItems){
    $jsonString = $todoItem | ConvertTo-Json -Compress
    $response = Invoke-WebRequest -Uri $url -Method Post -Body $jsonString -Headers @{"Content-Type"="application/json"}
    if($null -ne $response){
        Write-Host "---", $response.RawContent, "---" -Separator "`n"
    }
}