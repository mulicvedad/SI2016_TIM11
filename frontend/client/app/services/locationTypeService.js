import BaseService from './baseService';

export default class LocationTypeService extends BaseService {
	all() {
		return super.get('locationTypes/all');
	}

	create(locType) {
		return super.post('locationTypes', locType);
	}

	getPage(page)
	{
		return super.get('locationTypes/page/'+page);
	}

	delete(id) {
		return super.delete('locationTypes/' + id);
	}
}
