import {LayoutView} from 'backbone.marionette';
import {region} from '../../decorators';
import template from './TestsuiteView.hbs';
import TestcaseTableView from '../testcase-table/TestcaseTableView';

export default class TestsuiteView extends LayoutView {
    template = template;

    @region('.testsuite__testcases')
    testcases;

    initialize({testsuite, state}) {
        this.state = state;
        this.listenTo(this.state, 'change:testcase', this.showTestcases, this);
        this.model = testsuite;
    }

    onRender() {
        this.showTestcases();
    }

    showTestcases() {
        this.testcases.show(new TestcaseTableView({
            testCases: this.model.get('testCases'),
            currentCase: this.state.get('testcase'),
            baseUrl: 'xUnit/' + this.state.get('testsuite')
        }));
    }

    serializeData() {
        return Object.assign({
            baseUrl: this.options.baseUrl
        }, super.serializeData());
    }
}