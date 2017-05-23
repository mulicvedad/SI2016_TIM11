import template from './errorDisplay.html';

let errorDisplayComponent = {
    restrict: 'E',
    bindings: {        
        content: '<'
    },
    template
}

export default errorDisplayComponent;