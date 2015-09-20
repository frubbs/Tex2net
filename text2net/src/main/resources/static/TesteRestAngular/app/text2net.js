angular.module('Text2net',['ngResource'])
.controller('ConnectionCtrl',function($scope,$resource) {
	
	 $scope.teste = 'opaqqq'; 
	
	 $scope.id = 4;
	// var Connection = $resource('http://localhost:8081/text2net/text2net/:id');	
	 var Connection = $resource('https://aqueous-springs-2352.herokuapp.com/text2net/:id');	
	
	
  var entry = Connection.get({ id: $scope.id }, function() {
    console.log(entry);
  }); // get() returns a single entry

  var entries = Connection.query(function() {
    console.log(entries);
  }); //query() returns all the entries

  $scope.entry = entry; //You can instantiate resource class
	
	
});