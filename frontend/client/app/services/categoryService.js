import BaseService from './baseService';

export default class CategoryService extends BaseService {
	all() {
		console.log("CATEGORIES GET ALL STTUEITUIEUT");
		return super.get('categories/all');
	}

	create(account) {
		return super.post('categories', account);
	}
}
