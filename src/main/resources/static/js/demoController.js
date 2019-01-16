demoControllers.controller("DemoController", ['$scope', '$http',
    function($scope, $http) { 
	
		$scope.edit = false;
		
		//Get salesforce contacts synced to postgres
		$scope.getContacts = function() {
			$scope.edit = false;
			$http.get("/getSfContacts")
		    .then(function(response) {
		        $scope.contacts = response.data;
		    });
		}
		
		$scope.editItem = function(contact) {
			$scope.edit = true;
			$scope.contact = contact;
		}
		
		//Save a contact to postgres which in turn syncs to salesforce
		$scope.save = function(contact) {
			
			$http.post("/saveSfContact", contact).then(function(response) {
				$scope.status = response.data;
				if(response.data) {
					$scope.getContacts();
				}
		    });
			
		}
		
		$scope.cancel = function() {
			$scope.edit = false;
		}
	}
]);