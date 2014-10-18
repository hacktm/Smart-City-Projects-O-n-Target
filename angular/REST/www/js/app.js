// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
angular.module('yo', ['ionic','activityApp.controllers','activityApp.services'])

  .config(function($stateProvider){
          $stateProvider.state('activities',{
             url:'/activities',
             controller:'ActivityListController',
             templateUrl:'views/activities.html'
          }).state('createActivity',{
              url:'/activities/new',
              controller:'ActivityCreationController',
              templateUrl:'views/create-activity.html'
          }).state('editActivity',{
              url:'/activities/edit/:id/:content',
              controller:'ActivityEditController',
              templateUrl:'views/edit-activity.html'
          });
  }) 


  .run(function($ionicPlatform, $state) {
    $ionicPlatform.ready(function() {
      // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
      // for form inputs)
      if(window.cordova && window.cordova.plugins.Keyboard) {
        cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
      }
      if(window.StatusBar) {
        StatusBar.styleDefault();
      }
    });

    $state.go('activities');

  })
