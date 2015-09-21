angular.module('text2net',[]) //text2net is our main module. Depends on ngResource['ngResource']
.controller('ConnectionCtrl',function($scope) {
	
   $scope.teste = 'opa'; 	
	/*
 var Connection = $resource('http://localhost:8080/text2net/text2net/:id');	
	
  var entry = Connection.get({ id: $scope.id }, function() {
    console.log(entry);
  }); // get() returns a single entry

  var entries = Connection.query(function() {
    console.log(entries);
  }); //query() returns all the entries

  $scope.entry = entry; //You can instantiate resource class

  //$scope.entry.data = 'some data';	*/
	
});