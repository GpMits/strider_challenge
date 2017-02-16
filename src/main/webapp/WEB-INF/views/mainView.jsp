<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>  
    <title>Task Manager</title>  
    <style>
      .username.ng-valid {
          background-color: white;
      }
      .username.ng-dirty.ng-invalid-required {
          background-color: lightred;
      }

    </style>
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
     <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
     <link href="<c:url value='/static/css/animation.css' />" rel="stylesheet"></link>
     

  </head>
  <body ng-app="myApp" class="ng-cloak">
      <div class="generic-container" ng-controller="TaskController as ctrl">
          <div class="panel panel-default">
              <div class="panel-heading"><span class="lead">Insert new task</span></div>
              <div class="formcontainer">
                  <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
                      <input type="hidden" ng-model="ctrl.user.id" />
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Description</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.task.description" name="desc" class="username form-control input-sm" placeholder="Enter a description for the task" required/>
                              </div>
                          </div>
                      </div>
                      
                      <div class="row">
                          <div class="form-actions floatRight">
                              <input type="submit"  value="Add" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                              <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
                          </div>
                      </div>
                  </form>
              </div>
          </div>
          <div class="panel panel-default">
                <!-- Default panel contents -->
              <div class="panel-heading"><span class="lead">List of tasks </span></div>
              <div class="tablecontainer">
                  <table class="table table-hover">
                      <thead>
                          <tr>
                              <th>ID.</th>
                              <th>Description</th>
                              <th>Status</th>
                              <th></th>
                          </tr>
                      </thead>
                      <tbody>
                          <tr ng-repeat-start="u in ctrl.tasks">
                              <td><span ng-bind="u.id"></span></td>
                              <td><span ng-bind="u.description"></span></td>
                              <td><span ng-bind="u.taskStatus.name"></span></td>
                              <td>
                              	<button type="button" ng-click="show = !show" class="btn btn-info custom-width" ng-disabled="ctrl.check(u)">Expand</button>
                              	<button type="button" ng-click="ctrl.remove(u.id)" class="btn btn-danger custom-width">Remove</button>
                              </td>
                           	  </tr>
                           	  <tr ng-repeat-end ng-show="show" class="sample-show-hide">
                              <td colspan=4 align="center">	
                              	<!-- div ng-hide="!show" class="fadein fadeout"-->
                              	<div class="sample-show-hide" ng-show="show">
                              		<img ng-src="/static/image/{{u.img}}" class="img-responsive img-thumbnail">
                              		</div>
                              	</div>
                              </td>
                          </tr>
                      </tbody>
                  </table>
              </div>
          </div>
      </div>
      
      <script src="//code.angularjs.org/snapshot/angular.min.js"></script>
      <script src="//code.angularjs.org/snapshot/angular-animate.js"></script>
      <script src="<c:url value='/static/js/app.js' />"></script>
      <script src="<c:url value='/static/js/service/task_service.js' />"></script>
      <script src="<c:url value='/static/js/controller/task_controller.js' />"></script>
  </body>
</html>