import ItemsCRDModule from './itemsCRD'
import ItemsCRDController from './itemsCRD.controller';
import ItemsCRDComponent from './itemsCRD.component';
import ItemsCRDTemplate from './itemsCRD.html';

describe('ItemsCRD', () => {
  let $rootScope, makeController;

  beforeEach(window.module(ItemsCRDModule));
  beforeEach(inject((_$rootScope_) => {
    $rootScope = _$rootScope_;
    makeController = () => {
      return new ItemsCRDController();
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
      expect(ItemsCRDTemplate).to.match(/{{\s?\$ctrl\.name\s?}}/g);
    });
  });

  describe('Component', () => {
      // component/directive specs
      let component = ItemsCRDComponent;

      it('includes the intended template',() => {
        expect(component.template).to.equal(ItemsCRDTemplate);
      });

      it('invokes the right controller', () => {
        expect(component.controller).to.equal(ItemsCRDController);
      });
  });
});
