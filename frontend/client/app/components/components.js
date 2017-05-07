import angular from 'angular';
import Home from './home/home';
import About from './about/about';
import Accounts from './accounts/accounts';
import Locations from './locations/locations';
import Categories from './categories/categories';
import Status from './status/status';

let componentModule = angular.module('app.components', [
  Home,
  About,
  Accounts,
  Locations,
  Categories,
  Status
])

.name;

export default componentModule;
