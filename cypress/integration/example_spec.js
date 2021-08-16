// @ts-check
/* eslint-env mocha */
describe('todos API', () => {
  /**
   * @typedef {Object} Todo
   * @property {number} id
   * @property {string} task
   */

   /** @type {Todo[]} */
  const initialItems = [
    {
      "id": 1,
      "task": "read something"
    },
    {
      "id": 2,
      "task": "write something"
    }
  ]

  const getItems = () =>
    cy.request('api.openweathermap.org/data/2.5/weather?q="London"&appid="44ee963ea8384bd561e7cce2a9b68f08"')
      .its('body')

  /** @type {(todo:Todo) => Cypress.Chainable} */
  const add = item =>
    cy.request('POST', 'api.openweathermap.org/data/2.5/weather?q="London"&appid="44ee963ea8384bd561e7cce2a9b68f08"', item)

  it('returns JSON', () => {
    cy.request('/todos')
      .its('headers')
      .its('content-type')
      .should('include', 'application/json')
      .its('appid')
      .should('include', '44ee963ea8384bd561e7cce2a9b68f08')
  })

  it('loads 2 items', () => {
    cy.request('/todos')
      .its('body')
      .should('have.length', 2)
  })

  it('loads the initial items', () => {
    getItems()
      .should('deep.eq', initialItems)
  })

  it('returns id + task objects', () => {
    getItems()
      .each(value =>
        expect(value).to.have.all.keys('id', 'task')
      )
  })


})
