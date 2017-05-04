import angular from 'angular';
import uiRouter from 'angular-ui-router';
import Common from './common/common';
import Components from './components/components';
import services from './services/services';
import AppComponent from './app.component';
import 'normalize.css';
import 'materialize-loader';

angular.module('app', [
    uiRouter,
    Common,
    Components,
    services.name
  ])
  .constant('ENV', {
    APIURL: 'http://localhost:8080/',
    BASEURL: '/',
    ENABLEDEBUG: true
  })
  .config(($locationProvider) => {
    "ngInject";
    // @see: https://github.com/angular-ui/ui-router/wiki/Frequently-Asked-Questions
    // #how-to-configure-your-server-to-work-with-html5mode
    $locationProvider.html5Mode(true).hashPrefix('!');
  })
  .component('app', AppComponent);
