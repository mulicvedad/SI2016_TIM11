import BaseService from './baseService';

export default class LocationTypeService extends BaseService {
	all() {
		return super.get('locationTypes/all');
	}

	create(locType) {
		return super.post('locationTypes', locType);
	}

	delete(index){
		//..
	}
}
