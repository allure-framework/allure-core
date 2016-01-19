import {Behavior} from 'backbone.marionette';
import TooltipView from '../components/tooltip/TooltipView';
import {on} from '../decorators';

export default class TooltipBehavior extends Behavior {

    initialize() {
        this.tooltip = new TooltipView(this.options);
    }

    onDestroy() {
        this.tooltip.hide();
    }

    @on('mouseenter [data-tooltip]')
    onTipHover(e) {
        const el = this.$(e.currentTarget);
        this.tooltip.show(el.data('tooltip'), el);
    }

    @on('mouseleave [data-tooltip]')
    onTipLeave() {
        this.tooltip.hide();
    }
}
