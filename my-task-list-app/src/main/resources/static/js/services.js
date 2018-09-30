angular.module('TaskApp.services', [])
	.service("taskAPIservice",function($http, $q) {
        
		return ({
            getTasks: getTasks,
            getTasksByStatus: getTasksByStatus,
            createTask: createTask,
			updateTask: updateTask,
			deleteTask: deleteTask
        });

		function getTasks(){
			var request = $http.get("http://localhost:8080/api/tasks");  
			return (request.then(handleSuccess, handleError));
		}
	
		function getTasksByStatus(status) {
			var request = $http.get("http://localhost:8080/api/tasks/status/"+status);  
			return (request.then(handleSuccess, handleError));
		}
	
		function createTask(input){
			var request = $http.post("http://localhost:8080/api/tasks",input);
			return (request.then(handleSuccess, handleError));
		};
		
		function updateTask(selectedRecord){
			var request = $http.put("http://localhost:8080/api/tasks/"+selectedRecord.taskId, selectedRecord);	
			return (request.then(handleSuccess, handleError));
		};
	
		function deleteTask(selectedId){
			var request = $http.delete("http://localhost:8080/api/tasks/"+selectedId);
			return (request.then(handleSuccess, handleError));
		};

		function handleError(response) {
			if (!angular.isObject(response.data) || !response.data.message) {
                return ($q.reject("An unknown error occurred."));
            }
            // Otherwise, use expected error message.
            return ($q.reject(response.data.message));
        }

		function handleSuccess(response) {
            return (response.data);
        }

});