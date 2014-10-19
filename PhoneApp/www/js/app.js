// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
angular.module('EnjoyCity', ['ionic', 'category.services', 'category.controllers', 'gmaps.controllers', 'content.services', 'content.controllers', 'home.controllers'])

.config(['$httpProvider', '$stateProvider', function( $httpProvider, $stateProvider){

          $stateProvider.state('main',{
             url:'/home',
             controller:"HomeController",
             templateUrl:'views/home.html'
          }).state('categoryView',{
             url:'/category',
             controller:'CategoryViewController',
             templateUrl:'views/categoryView.html'
          }).state('gmapsView',{
              url:'/gmaps',
              controller:'MapCtrl',
              templateUrl:'views/gmapsView.html'
          });

          // .state('editActivity',{
          //     url:'/category/edit/:id/:content',
          //     controller:'ActivityEditController',
          //     templateUrl:'views/edit-activity.html'
          // });

        $httpProvider.defaults.useXDomain = true;
        delete $httpProvider.defaults.headers.common['X-Requested-With'];
  }]) 

.run(function($ionicPlatform, $state) {

  $ionicPlatform.ready(function() {
    // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
    // for form inputs)$httpProvider
    if(window.cordova && window.cordova.plugins.Keyboard) {
      cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
    }
    if(window.StatusBar) {
      StatusBar.styleDefault();
    }
  });

  $state.go('main');
})
