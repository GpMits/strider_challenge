'use strict';

angular.module('myApp').factory('TaskService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/task/';

    var factory = {
        fetchAllTasks: fetchAllTasks,
        createTask: createTask,
        updateUser:updateUser,
        deleteTask:deleteTask
    };

    return factory;

    function fetchAllTasks() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI+'list')
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Tasks');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function createTask(task) {
        var deferred = $q.defer();
        console.log(task)
        $http.post(REST_SERVICE_URI+'save', task)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating Task');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }


    function updateUser(user, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+id, user)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating User');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function deleteTask(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+'delete/'+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting Task');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

}]);
