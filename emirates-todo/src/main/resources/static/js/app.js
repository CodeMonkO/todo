var app = angular.module('app', ['ngRoute','ngResource']);
app.config(function($routeProvider){
    $routeProvider
        .when('/',{
            templateUrl: '/welcome',
            controller: 'mainController'
        })
        .otherwise(
            { redirectTo: '/'}
        );
});