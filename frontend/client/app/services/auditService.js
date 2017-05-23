import BaseService from './baseService';

export default class AuditService extends BaseService {
	all() {
		return super.get('audits/all');
       
	}
}
