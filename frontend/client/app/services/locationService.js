import BaseService from './baseService';

export default class LocationService extends BaseService {
	all() {
		return super.get('locations/all');
	}

	create(account) {
		return super.post('locations', account);
	}

	getPage(page)
	{
		return super.get('locations/page/'+page);
	}
}
