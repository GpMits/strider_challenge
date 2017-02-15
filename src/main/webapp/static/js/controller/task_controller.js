'use strict';

angular.module('myApp').controller('TaskController', ['$scope', 'TaskService', function($scope, TaskService) {
    var self = this;
    self.task={id:3,description:'',user:null,taskStatus:null};
    self.tasks=[];
    
    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
    self.check = check;


    fetchAllTasks();

    function fetchAllTasks(){
        TaskService.fetchAllTasks()
            .then(
            function(d) {
                self.tasks = d;
            },
            function(errResponse){
                console.error('Error while fetching Users');
            }
        );
    }

    function createTask(task){
        TaskService.createTask(task)
            .then(
            fetchAllTasks,
            function(errResponse){
                console.error('Error while creating Task');
            }
        );
    }

    function updateUser(user, id){
        UserService.updateUser(user, id)
            .then(
            fetchAllUsers,
            function(errResponse){
                console.error('Error while updating User');
            }
        );
    }

    function deleteTask(id){
        TaskService.deleteTask(id)
            .then(
            fetchAllTasks,
            function(errResponse){
                console.error('Error while deleting Task');
            }
        );
    }

    function submit() {
        console.log('Saving New Task', self.task);
        createTask(self.task);
        reset();
    }

    function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.users.length; i++){
            if(self.users[i].id === id) {
                self.user = angular.copy(self.users[i]);
                break;
            }
        }
    }

    function remove(id){
        console.log('id to be deleted', id);
        if(self.task.id === id) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deleteTask(id);
    }


    function reset(){
    	self.task={id:3,description:'',user:null,taskStatus:null};
        $scope.myForm.$setPristine(); //reset Form
    }
    
    function check(tsk){
    	if(tsk.taskStatus.name == "Finished") return false;
    	return true;
    }
    
}]);
