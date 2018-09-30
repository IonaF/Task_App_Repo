angular.module('TaskApp.controllers', [])
	.controller('tasksController', function($scope, taskAPIservice){
    
		$scope.nameFilter = null;
		$scope.taskList = [];
		$scope.taskStatus = "";
        
		function listTasks(){
			$scope.errorDisplay = "";
			$scope.showErrorMessage = false;
			if($scope.taskStatus !== ""){
				taskAPIservice.getTasksByStatus($scope.taskStatus).then(function (response) {
					$scope.taskList = response;
				});
			}else{
				taskAPIservice.getTasks().then(function (response) {
					$scope.taskList = response;
				});
			}
		}
	
		listTasks();
	
		$scope.statusChanged = function(){
			listTasks();
		}

		$scope.addTask = function(isFormValid){
			if(isFormValid){
				$scope.errorDisplay = "";
				$scope.showErrorMessage = false;
				var newTask = {
					title : $scope.taskText
				};
				
				taskAPIservice.createTask(newTask).then(listResultAndClearForm,
                    function(errorMessage) {
						$scope.taskText = "";
						if(errorMessage.indexOf("E11000") !== -1){
							$scope.errorDisplay = newTask.title +" already exists.";
						}else{
							$scope.errorDisplay = errorMessage;
						}
                        
						$scope.showErrorMessage = true;
                    }
				);
			}
		};
		
		function listResultAndClearForm(){
			$scope.taskText = "";
			listTasks();
		}
		
		$scope.deleteTask = function(idx){
			taskAPIservice.deleteTask(idx).then(
				listTasks,
				function(errorMessage) {
					$scope.errorDisplay = errorMessage;
					$scope.showErrorMessage = true;
				}
			);
		};
		
		$scope.markTaskAsComplete = function(row){
			taskAPIservice.updateTask(row).then(
				listTasks,
				function(errorMessage) {
					$scope.errorDisplay = errorMessage;
					$scope.showErrorMessage = true;
				}
			);
		};
    }
);