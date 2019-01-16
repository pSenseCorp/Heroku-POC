<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.7.2/angular.min.js"></script>
 <script type="text/javascript" src="js/demo.js"></script>
 <script type="text/javascript" src="js/demoController.js"></script>
 
 
<h1>Heroku-Salesforce Integration Demo</h1>
 
 <div ng-app="DemoApp" ng-controller="DemoController">
 
 	<div> <button ng-click="getContacts();">Get Salesforce Contacts</button> </div>
 	<br>
 
	<div ng-if="contacts && !edit">
		<h2>Contacts</h2>
		<table>
			<tr>
				<th><span><b>Name:</b></span></th>
				<th><span><b>Phone:</b></span></th>
				<th></th>
			</tr>
			<tr ng-repeat="contact in contacts">
		    		<td>{{contact.name}}</td>
		    		<td>{{contact.phone}}</td>
		    		<td><button ng-click="editItem(contact)">Edit</button></td>
		    </tr>
			
		</table>
	</div>
	
	<div ng-if="edit">
		<div>
			<span>Name:</span> <span><input type="text" ng-model="contact.name"/></span>
			<span>Phone:</span> <span><input type="text" ng-model="contact.phone"/></span>
		</div>
		<br>
		<div>
			<span><button ng-click="save(contact)">Save</button><span> <span><button ng-click="cancel()">Cancel</button></span>
		</div>
	</div>
</div>
