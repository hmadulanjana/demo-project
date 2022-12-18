import { useRef, useEffect, Fragment, Suspense } from "react"
import axios from "axios"
import { 
  useStore,
  // get,
} from '../utils/barrel'
import shallow from 'zustand/shallow'

export const ItemList = (props) => {

  const [items, setItems] = useStore(
		(state) => [state.items, state.setItems],
		shallow
	)

  const [urls, entities] = useStore(
		(state) => [state.urls, state.entities],
		shallow
	)

  const fetchData = async(url, entity) =>
  {
    const data = await axios.get(`${url}${entity}`).then(res => res.data)
    setItems(entity, data)
  }


  // const itemsRef = useRef(useStore.getState().items)

  // useEffect(() => useStore.subscribe(
  //   items => (itemsRef.current = items),
  //   state => state.items
  // ), [])

  // const sleep = ms => new Promise(r => setTimeout(r, ms))

  let data = null;

  useEffect(() => {
    console.log(items)
  }, [items])

  // if (!items.length) return <h3>Loading...</h3> // Disable to show errors clearly during dev

  return (
  <div className='m-auto'>
    {/* <button className='bg-[radial-gradient(ellipse_at_top,_var(--tw-gradient-stops))] from-gray-200 via-gray-400 to-gray-600 rounded-lg border-solid border-8 border-orange-300' onClick={() => setItems([])}>Reset List</button> */}

  {/* <>Suspense</><br /> */}
  <button onClick={() => {
    fetchData(`${urls[0]}`, entities[0])
    fetchData(`${urls[0]}`, entities[1])
    fetchData(`${urls[0]}`, entities[2])
    fetchData(`${urls[0]}`, entities[3])
  }} className='border-solid border-8 border-blue-300 space-y-2'>Fetch</button><br />

  {/* <Suspense fallback={<>Fetching...</>}> */}

  <div className='flex flex-row bg-black rounded-lg z-0 space-x-2'>
  <div className=''>
    {items.courses?.map((item) => (
        ('courseId' in item) === true ?
        <Fragment key={item.courseId}>
          <div className="bg-white rounded-lg p-2">
          <>(Course)</><br />
            <>{item.courseName}</><br />
            <>Instructor: {item.id}</><br />
          </div>
          <br />
        </Fragment>
    : null))}
  </div>

  <div className=''>

      {items.enrollments?.map((item) => (
          ('courseId' in item && 'studentId' in item) === true ?
          <Fragment key={'enrol' + item.courseId + item.studentId}>
            <div className="bg-white rounded-lg p-2">
            <>(Enrollment)</><br />
              <>Course: {item.courseId}</><br />
              <>Enrolled Students: {item.studentId}</><br />
            </div>
            <br />
          </Fragment>
      : null))}
    </div>

      <div className=''>
      {items.students?.map((item) => (
          ('studentId' in item) === true ?
          <Fragment key={item.studentId}>
            <div className="bg-white rounded-lg p-2">
            <>(Student)</><br />
              <>{item.studentName}</><br />
              <>{item.emailId}</><br />
            </div>
            <br />
          </Fragment>
      : null))}
      </div>

      <div className=''>
      {items.users?.map((item) => (
          ('id' in item) === true ?
          <Fragment key={item.id}>
            <div className="bg-white rounded-lg p-2">
            <>(Instructor)</><br />
              <>{item.userName}</><br />
              <>{item.emailId}</><br />
            </div>
            <br />
          </Fragment>
      : null))}
      </div>

    </div>
  </div>
  )
}
