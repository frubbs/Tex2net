angular.module('Text2net',['ngResource'])
.controller('ConnectionCtrl',function($scope,$http) {
	  
	  $scope.submitForm = function() {
			console.log('samba');
        };
		
	 $scope.teste = 'opaqqq'; 
	
	$http.get('http://aqueous-springs-2352.herokuapp.com/text2net/1').
        success(function(data) {
            $scope.greeting = data;
				console.log(data);
    });
	
	
  var processText = {text:'O @@DOUBLE_NW@@ PORTARIA N 4.220, DE 13 DE DEZEMBRO DE 2012 @@DOUBLE_NW@@ Em Aditamento da Portaria Nº 0019 de 12 de Abril de 2010O PROCURADOR DO TRABALHO, que esta subscreve, nouso de suas atribuições legais,Considerando o teor dos fatosrelatados no procedimento nºPP 003183.2009.01.003/9 - 301, instaurado a partir de duas denúnciasanônimas formuladas nestaProcuradoria Regional do Trabalho da 1ªRegião Procuradoria do Trabalho no Município de Campos dosGoytacazes, dando notícia de queas denunciadas, LIMPORT COMÉRCIO E SERVIÇOS LTDA e PORTLIMP COMÉRCIO E SERVIÇOS LTDA., vêm praticando irregularidades trabalhistas,concernentes à submissão dos trabalhadores a excesso de jornada de trabalho, pagamento do adicional noturno de formaincorreta, pagamentode férias de forma incorreta, não pagamento do trabalho realizado emferiados e imposição aos trabalhadores queos recibos sejam assinadosantes dos depósitos dos respectivos salários;Considerando o disposto nos arts. 127 e 129 da ConstituiçãodaRepública, art. 6º, VII e 84, III, da Lei Complementar nº 75/93 eart. 8º, §1º da Lei nº 7347/85, que atribuem aoMinistério Público doTrabalho a defesa dos interesses difusos, coletivos, sociais e individuais indisponíveis dos trabalhadores,resolve:Retificar o objeto investigado no Inquérito Civil Público nº003183.2009.01.003/9 - 301, para incluir o temaausência de intervalointrajornada. Continuará presidindo o inquérito o Procurador do Trabalho, DR. FRANCISCO CARLOS DA SILVA ARAÚJO,que poderá ser secretariado pelos servidores Carlos Eduardo Jacintho Lobo e Eduardo Xavier de Souza, Analistas Processuais.@@DOUBLE_NW@@FRANCISCO CARLOS DA SILVA ARAÚJO @@DOUBLE_NW@@Poder Judiciário.@@DOUBLE_NW@@TRIBUNAL REGIONAL DO TRABALHO13ªREGIÃO@@DOUBLE_NW@@ATO N 457, DE 12 DE DEZEMBRO DE 2012@@DOUBLE_NW@@O DESEMBARGADOR PRESIDENTE DO '};
  
  
  $http.post('https://aqueous-springs-2352.herokuapp.com/text2net/1', processText).
        success(function(data) {
            $scope.connections = data;
				console.log(data);
    });
	
	
	$scope.submitConnections = function() {
		console.log('noi');
		$http.post('https://aqueous-springs-2352.herokuapp.com/text2net/1', $scope.connectionQuery).
			success(function(data) {
				$scope.connections = data;
				console.log(data);
				
				
		});
		
	};
	
  
});