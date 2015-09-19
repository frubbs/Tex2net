function Hello($scope, $http) {
    $http.get('http://aqueous-springs-2352.herokuapp.com/text2net').
        success(function(data) {
            $scope.greeting = data;
        });
}