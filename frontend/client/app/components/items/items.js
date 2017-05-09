import angular from 'angular';
import uiRouter from 'angular-ui-router';
import itemsComponent from './items.component';

let itemsModule = angular.module('items', [
  uiRouter
])

.config(($stateProvider) => {
  "ngInject";
  $stateProvider
    .state('items', {
      url: '/items',
      component: 'items'
    });
})

.component('items', itemsComponent)

.name;

export default itemsModule;
