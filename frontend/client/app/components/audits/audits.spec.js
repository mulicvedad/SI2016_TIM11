import AuditsModule from './audits'
import AuditsController from './audits.controller';
import AuditsComponent from './audits.component';
import AuditsTemplate from './audits.html';

describe('Audits', () => {
  let $rootScope, makeController;

  beforeEach(window.module(AuditsModule));
  beforeEach(inject((_$rootScope_) => {
    $rootScope = _$rootScope_;
    makeController = () => {
      return new AuditsController();
    };
  }));

  describe('Module', () => {
    // top-level specs: i.e., routes, injection, naming
  });

  describe('Controller', () => {
    // controller specs
    it('has a name property [REMOVE]', () => { // erase if removing this.name from the controller
      let controller = makeController();
      expect(controller).to.have.property('name');
    });
  });

  describe('Template', () => {
    // template specs
    // tip: use regex to ensure correct bindings are used e.g., {{  }}
    it('has name in template [REMOVE]', () => {
      expect(AuditsTemplate).to.match(/{{\s?\$ctrl\.name\s?}}/g);
    });
  });

  describe('Component', () => {
      // component/directive specs
      let component = AuditsComponent;

      it('includes the intended template',() => {
        expect(component.template).to.equal(AuditsTemplate);
      });

      it('invokes the right controller', () => {
        expect(component.controller).to.equal(AuditsController);
      });
  });
});
