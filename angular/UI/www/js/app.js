// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
angular.module('UI', ['ionic'])

.controller('AppController', function($scope, $interval, $ionicSlideBoxDelegate) {

    var maxSlides = 5;
    var slideCounter = 2;

    $scope.data = {};
    $scope.data.slides = [
        {
            title : "Food",
            data  : ""
        },
        {
            title : "Cultural",
            data  : ""
        },
        {
            title : "Sport",
            data  : ""
        },
        {
            title : "Travel",
            data  : ""
        },
        {
            title : "Entertainment",
            data  : ""
        },
        {
            title : "Cinema",
            data  : ""
        },
        {
            title : "Beauty",
            data  : ""
        },
        {
            title : "New in town",
            data  : ""
        },
        {
            title : "Wellness",
            data  : ""
        }
    ];

    $ionicSlideBoxDelegate.update();

})

.run(function($ionicPlatform) {
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

})


