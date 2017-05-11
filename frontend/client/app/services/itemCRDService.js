import BaseService from './baseService';

export default class ItemCRDService extends BaseService {
	all() {
		return super.get('items/all');
       
	}

	create(item) {
		return super.post('items', item);
	}
}
