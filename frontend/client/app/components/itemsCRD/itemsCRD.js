import angular from 'angular';
import uiRouter from 'angular-ui-router';
import itemsCRDComponent from './itemsCRD.component';

let itemsCRDModule = angular.module('itemsCRD', [
  uiRouter
])

.config(($stateProvider) => {
  "ngInject";
  $stateProvider
    .state('itemsCRD', {
      url: '/itemsCRD',
      component: 'itemsCRD'
    });
})

.component('itemsCRD', itemsCRDComponent)

.name;

export default itemsCRDModule;
