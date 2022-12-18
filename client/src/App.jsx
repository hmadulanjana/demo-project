// import { useState } from 'react'

import reactLogo from './assets/react.svg'

import shallow from 'zustand/shallow'

import {
  // Components
  Item,
  Dropdown,
  Dialog,
  ItemList,
  FormLayout,
  FormSelector,
  // Mocks
  items,
  // Utils
  useStore
 } from './utils/barrel'

export default function App() {

  const isOpen = useStore((state) => state.isOpen)

  const [showForm, toggleForm] = useStore(
		(state) => [state.showForm, state.toggleForm],
		shallow
	)

  return (
    <div className='flex min-h-screen bg-green-200 py-4 space-y-4 snap-mandatory snap-x'>

      {/* {isOpen ? <Dialog /> : null} */}

      <div className='m-auto border-solid border-8 border-blue-300'>
        <button className='bg-[radial-gradient(ellipse_at_top,_var(--tw-gradient-stops))] from-gray-200 via-gray-400 to-gray-600 rounded-lg border-solid border-8 border-orange-300' onClick={toggleForm}>Request Form</button>
        {true && showForm ? <FormLayout>
          <FormSelector />
        </FormLayout> : null}

        {/* {true && showForm ? <FormLayout>
          <>???</>
        </FormLayout> : null} */}
      </div>

      <ItemList />
    </div>
  )
}
