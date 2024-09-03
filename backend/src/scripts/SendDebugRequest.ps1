cls
cd $PSScriptRoot
$rootUrl = "http://localhost:9999/debug/todoitem"
for($i = 0; $i -lt 10; $i++){
    $url = $rootUrl + "?index=$($i)"
    $response = Invoke-WebRequest -Uri $url -ErrorAction SilentlyContinue
    if($null -ne $response){
        $response.RawContent
    }
}
Remove-Variable 'response'