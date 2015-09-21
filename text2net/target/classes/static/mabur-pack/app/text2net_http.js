angular.module('Text2net',[])
.controller('ConnectionCtrl',function($scope,$http) {
		
		
		
		
	var targetURL = 'http://aqueous-springs-2352.herokuapp.com/text2net/1';
//	var targetURL = 'http://localhost:8081/text2net/text2net/1';
	 
	  $scope.submitForm = function() {
			console.log('samba');
        };
		
	 $scope.teste = 'opaqqq'; 
	
	$http.get(targetURL).
        success(function(data) {
            $scope.greeting = data;
				console.log(data);
    });
	
	
    $scope.processText = {text:'O @@DOUBLE_NW@@ PORTARIA N 4.220, DE 13 DE DEZEMBRO DE 2012 @@DOUBLE_NW@@ Em Aditamento da Portaria Nº 0019 de 12 de Abril de 2010O PROCURADOR DO TRABALHO, que esta subscreve, nouso de suas atribuições legais,Considerando o teor dos fatosrelatados no procedimento nºPP 003183.2009.01.003/9 - 301, instaurado a partir de duas denúnciasanônimas formuladas nestaProcuradoria Regional do Trabalho da 1ªRegião Procuradoria do Trabalho no Município de Campos dosGoytacazes, dando notícia de queas denunciadas, LIMPORT COMÉRCIO E SERVIÇOS LTDA e PORTLIMP COMÉRCIO E SERVIÇOS LTDA., vêm praticando irregularidades trabalhistas,concernentes à submissão dos trabalhadores a excesso de jornada de trabalho, pagamento do adicional noturno de formaincorreta, pagamentode férias de forma incorreta, não pagamento do trabalho realizado emferiados e imposição aos trabalhadores queos recibos sejam assinadosantes dos depósitos dos respectivos salários;Considerando o disposto nos arts. 127 e 129 da ConstituiçãodaRepública, art. 6º, VII e 84, III, da Lei Complementar nº 75/93 eart. 8º, §1º da Lei nº 7347/85, que atribuem aoMinistério Público doTrabalho a defesa dos interesses difusos, coletivos, sociais e individuais indisponíveis dos trabalhadores,resolve:Retificar o objeto investigado no Inquérito Civil Público nº003183.2009.01.003/9 - 301, para incluir o temaausência de intervalointrajornada. Continuará presidindo o inquérito o Procurador do Trabalho, DR. FRANCISCO CARLOS DA SILVA ARAÚJO,que poderá ser secretariado pelos servidores Carlos Eduardo Jacintho Lobo e Eduardo Xavier de Souza, Analistas Processuais.@@DOUBLE_NW@@FRANCISCO CARLOS DA SILVA ARAÚJO @@DOUBLE_NW@@Poder Judiciário.@@DOUBLE_NW@@TRIBUNAL REGIONAL DO TRABALHO13ªREGIÃO@@DOUBLE_NW@@ATO N 457, DE 12 DE DEZEMBRO DE 2012@@DOUBLE_NW@@O DESEMBARGADOR PRESIDENTE DO '};
    
	$scope.queryResults = [];
	
	$scope.submitConnections = function() {
		console.log('noi');
		$http.post(targetURL, $scope.connectionQuery).
			success(function(data) {
				//$scope.connections = data.connections;
				//$scope.markedUpText = data.markedUpText;
				data.name = $scope.connectionQuery.name; //isso eh mau
				$scope.queryResults.push(data)
				console.log(data);
		});
		
	};
	
  
	$scope.generatePajek = function(connectionList) {
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
		
		return header + '\n' + vertices.join('\n') + '\n' + edgesHeader + '\n' + edges.join('\n');
		
		
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
		
	}
  
	$scope.showQueryResult = function(queryResult) {
		console.log('opa' + queryResult);
		queryResult.clicked = true;
	}
  
  
  
  
});