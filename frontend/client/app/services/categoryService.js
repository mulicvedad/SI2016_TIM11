import BaseService from './baseService';

export default class CategoryService extends BaseService {
	all() {
		return super.get('categories/all');
	}

	create(account) {
		return super.post('categories', account);
	}
}
