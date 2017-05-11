import angular from 'angular';
import Home from './home/home';
import About from './about/about';
import Accounts from './accounts/accounts';
import Locations from './locations/locations';
import LocationTypes from './locationTypes/locationTypes';
import Categories from './categories/categories';
import Status from './status/status';
import Items from './items/items';
import ItemsCRD from './itemsCRD/itemsCRD';

let componentModule = angular.module('app.components', [
  Home,
  About,
  Accounts,
  Locations,
  Categories,
  Status,
  LocationTypes,
  Items,
  ItemsCRD    
])

.name;

export default componentModule;
