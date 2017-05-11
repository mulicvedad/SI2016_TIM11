import BaseService from './baseService';

export default class StatusService extends BaseService {
	all() {
		console.log("STATUS GET ALL");
		return super.get('status/all');
	}

	create(status) {
    console.log("STATUS CREATE");
		return super.post('status', status);
	}

    getPage(page)
    {
        return super.get('status/page/'+page);
    }
}
