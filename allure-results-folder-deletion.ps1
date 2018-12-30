$currentLocation = (Get-Location).Path
$allureResultsFolderLocation1 = $currentLocation+ '\allure-results'


if ((Test-Path $allureResultsFolderLocation1)) {
Remove-Item -Path $allureResultsFolderLocation1 -Force -Recurse -ErrorAction SilentlyContinue
}

