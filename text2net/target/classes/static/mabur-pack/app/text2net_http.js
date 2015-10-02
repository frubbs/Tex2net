angular.module('Text2net', ["ngSanitize"])
.controller('ConnectionCtrl',function($scope,$http) {
		
		
		
		
//	var targetURL = 'http://aqueous-springs-2352.herokuapp.com/text2net/1';
	var targetURL = 'http://localhost:8081/text2net/text2net/1';
	 
	  $scope.submitForm = function() {
			console.log('samba');
        };
		
	 $scope.teste = 'opaqqq'; 
	
	$http.get(targetURL).
        success(function(data) {
            $scope.greeting = data;
				console.log(data);
    });
	
	
    $scope.processText = {text:'O @@DOUBLE_NW@@ PORTARIA N 4.220, DE 13 DE DEZEMBRO DE 2012 @@DOUBLE_NW@@ Em Aditamento da Portaria NÂº 0019 de 12 de Abril de 2010O PROCURADOR DO TRABALHO, que esta subscreve, nouso de suas atribuiÃ§Ãµes legais,Considerando o teor dos fatosrelatados no procedimento nÂºPP 003183.2009.01.003/9 - 301, instaurado a partir de duas denÃºnciasanÃ´nimas formuladas nestaProcuradoria Regional do Trabalho da 1ÂªRegiÃ£o Procuradoria do Trabalho no MunicÃ­pio de Campos dosGoytacazes, dando notÃ­cia de queas denunciadas, LIMPORT COMÃ‰RCIO E SERVIÃ‡OS LTDA e PORTLIMP COMÃ‰RCIO E SERVIÃ‡OS LTDA., vÃªm praticando irregularidades trabalhistas,concernentes Ã  submissÃ£o dos trabalhadores a excesso de jornada de trabalho, pagamento do adicional noturno de formaincorreta, pagamentode fÃ©rias de forma incorreta, nÃ£o pagamento do trabalho realizado emferiados e imposiÃ§Ã£o aos trabalhadores queos recibos sejam assinadosantes dos depÃ³sitos dos respectivos salÃ¡rios;Considerando o disposto nos arts. 127 e 129 da ConstituiÃ§Ã£odaRepÃºblica, art. 6Âº, VII e 84, III, da Lei Complementar nÂº 75/93 eart. 8Âº, Â§1Âº da Lei nÂº 7347/85, que atribuem aoMinistÃ©rio PÃºblico doTrabalho a defesa dos interesses difusos, coletivos, sociais e individuais indisponÃ­veis dos trabalhadores,resolve:Retificar o objeto investigado no InquÃ©rito Civil PÃºblico nÂº003183.2009.01.003/9 - 301, para incluir o temaausÃªncia de intervalointrajornada. ContinuarÃ¡ presidindo o inquÃ©rito o Procurador do Trabalho, DR. FRANCISCO CARLOS DA SILVA ARAÃšJO,que poderÃ¡ ser secretariado pelos servidores Carlos Eduardo Jacintho Lobo e Eduardo Xavier de Souza, Analistas Processuais.@@DOUBLE_NW@@FRANCISCO CARLOS DA SILVA ARAÃšJO @@DOUBLE_NW@@Poder JudiciÃ¡rio.@@DOUBLE_NW@@TRIBUNAL REGIONAL DO TRABALHO13ÂªREGIÃƒO@@DOUBLE_NW@@ATO N 457, DE 12 DE DEZEMBRO DE 2012@@DOUBLE_NW@@O DESEMBARGADOR PRESIDENTE DO '};
    
	$scope.queryResults = [];
	
	$scope.submitConnections = function() {
		console.log('noi');
		$scope.loading = true; //loading
		
		//var submitText = '@@DOUBLE_NW@@  ' + $scope.connectionQuery.text + '  @@DOUBLE_NW@@';
		
		$http.post(targetURL, $scope.connectionQuery).
			then(function(response) {
				$scope.queryResults.push(response.data)
				console.log(response.data);
				$scope.selectedQueryResult = response.data;
				$scope.pajekNetwork = mergeToPajek($scope.queryResults);
				$scope.loading = false; //loading
			}, function(response) {
				$scope.loading = false; //loading
				$scope.selectedQueryResult = {"markedUpText" : "OOps! Error while processing request. Try again later."};
		});
		
		
		
		/*	success(function(data) {
				//$scope.connections = data.connections;
				//$scope.markedUpText = data.markedUpText;
				//data.name = $scope.connectionQuery.name; //isso eh mau
				$scope.queryResults.push(data)
				console.log(data);
				$scope.selectedQueryResult = data;
				$scope.pajekNetwork = mergeToPajek($scope.queryResults);
				$scope.loading = false; //loading
		});
		*/
	};
	
  
       var mergeToPajek = function(queryResults) {
		var connectionList = [];
		
		queryResults.forEach(function(entry) {
			connectionList = connectionList.concat(entry.connections);
		});
		
		return generatePajek(connectionList);
		}
  
  
	    var generatePajek = function(connectionList) {
		var elements = ['Dummy, cause pajek is 1 based'];
		
		//List network elements
		connectionList.forEach(function(entry) {
			elements.push(entry.elementA.name);
			elements.push(entry.elementB.name);
		});
		
		//generate unique list for pajek ids
		var uniqueElements = uniq(elements);
		
		//generate vertices list
		var vertices = [];
		uniqueElements.forEach(function(entry, i) {
			var verticesLine = i + ' "' + entry + '"';
			vertices.push(verticesLine);
		});
		
		//generate edges list
		var edges = [];
		connectionList.forEach(function(entry) {
			var edgeLine = uniqueElements.indexOf(entry.elementA.name) + ' ' + uniqueElements.indexOf(entry.elementB.name) + ' ' + entry.distance;
			edges.push(edgeLine);
		});
		
		
		vertices.shift(); //remove dummy
		
		var header = '*Vertices ' + vertices.length; //-1 dummy
		
		var edgesHeader = '*Edges';
		
		//return header + '\n' + vertices.join('\n') + '\n' + edgesHeader + '\n' + edges.join('\n');
		return header + '<br/>' + vertices.join('<br/>') + '<br/>' + edgesHeader + '<br/>' + edges.join('<br/>');
		
		
	}
	
	
	
	
	function uniq(a) {
		var seen = {};
		return a.filter(function(item) {
			return seen.hasOwnProperty(item) ? false : (seen[item] = true);
		});
	};
	
	$scope.disposeConnection = function (queryResult) {
		var i = $scope.queryResults.indexOf(queryResult);
		if (i > -1) {
			$scope.queryResults.splice(i, 1);
		}
		$scope.selectedQueryResult = $scope.queryResults[0];
		$scope.pajekNetwork = mergeToPajek($scope.queryResults);
		
	}
  
	$scope.showQueryResult = function(queryResult) {
		console.log('opa' + queryResult);
		queryResult.clicked = true;
	}
  
 // $scope.content = $scope.selectedQueryResult.markedUpText;
  
  
});