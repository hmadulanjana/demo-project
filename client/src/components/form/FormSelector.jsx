import {
    formHandler,
    useStore,
} from '../../utils/barrel'
import shallow from 'zustand/shallow'
import {useRef, useState} from 'react'
// import IframeResizer from 'iframe-resizer-react'

// const [body, setBody] = useStore(
//     (state) => [state.body, state.setBody],
//     shallow
// )

// const bodyRef = useRef(useStore.getState().body)

// useEffect(() => useStore.subscribe(
//     body => (bodyRef.current = body),
//   state => state.body
// ), [])

// const body = {
// 	// studentName: name,
// 	// emailId: email,
// 	// studentId: id
// }

// const loggedIn = true
// const loggedOut = true

export const FormSelector = () => {

    const [inputs
        , setStudentInputs
        , setCourseInputs
        , setEnrolInputs
        , setUserInputs
        , setAuthInputs
        , loggedIn
        , urls
        // , login
        // , logout
    ] = useStore(
        (state) => [state.inputs
            , state.setStudentInputs
            , state.setCourseInputs
            , state.setEnrolInputs
            , state.setUserInputs
            , state.setAuthInputs
            , state.loggedIn
            // . state.login
            // , state.logout
            , state.urls
        ],
        shallow
    )

    const login = useStore((state) => state.login)
    const logout = useStore((state) => state.logout)

    // iframe handler
    const ref = useRef()
    const [height, setHeight] = useState("0px");
    const onLoad = () => {
      setHeight(ref.current.contentWindow.document.body.scrollHeight + "px");
    }

	return (
		<div className='m-auto'>

{/* Auth Forms */}
            {/* !loggedIn */}
            {/* {loggedIn ?
            <form className='border-solid border-8 border-blue-300' onSubmit={(e) => {
                formHandler.login(
                    urls[0]
                    , 'sec/login'
                    , 'Staff1'
                    , '123'
                )
                login()
            }}>
                <div className='bg-white'>(Login)</div><br />
                <input value={inputs.authObj.username} type="text" name="name" placeholder="Enter Name:"
                    onChange={(e) => setAuthInputs('username', e.target.value)}/>
                <input value={inputs.authObj.password} type="password" name="password" placeholder="Enter Pass:"
                    onChange={(e) => setAuthInputs('password', e.target.value)}/>
                <button className='bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded' type="submit">Login</button>
            </form> : null}
            <br />

            {loggedIn ?
            <form className='border-solid border-8 border-blue-300' onSubmit={(e) => {
                formHandler.logout(
                    e
                    , 'http://localhost:4000/logout'
                )
                // logout()
            }}>
                <div className='bg-white'>(Logout)</div><br />
                <button className='bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded' type="submit">Logout</button>
            </form> : null}
            <br /> */}

            {/* <div>
                <iframe
                    ref={ref}
                    onLoad={onLoad}
                    id="myFrame"
                    width="100%"
                    height={height}
                    scrolling="no"
                    frameBorder="0"
                    style={{
                        maxWidth: 640,
                        width: "100%",
                        overflow: "auto",
                    }}
                 src="http://localhost:4000/login"></iframe>
            </div> */}

            <div>
            {/* <IframeResizer
                log
                src="http://anotherdomain.com/iframe.html"
                style={{ width: '1px', minWidth: '100%'}}
            /> */}
                <iframe height='320px' src='http://localhost:4000/login'></iframe>
            </div>
            <br />

            <div>
                {/* <iframe src="http://localhost:4000/login"></iframe> */}
                <a className='bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded' href='http://localhost:4000/logout'>Logout</a>
            </div>
            <br />

            {/* <button className='bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded'
                onClick={() => {
                    window.location.replace('http://localhost:4000/login');
                }}
            >Login</button>
            <button className='bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded'
                onClick={() => {
                    window.location.replace('http://localhost:4000/logout');
                }}
            >Logout</button> */}
{/* Auth Forms */}
{/* Student Forms */}
			{loggedIn ?
            <form className='border-solid border-8 border-blue-300 space-y-2' onSubmit={(e) => formHandler.post(
                e,
                'sec/students',
                inputs.studentObj
            )}>
                <div className='bg-white'>(Student: POST)</div><br />
                <input value={inputs.studentObj.studentId} type="number" name="id" placeholder="Enter ID:"
                    onChange={(e) => setStudentInputs('studentId', Number(e.target.value))}/>

                <input value={inputs.studentObj.studentName} type="text" name="name" placeholder="Enter Name:"
                    onChange={(e) => setStudentInputs('studentName', e.target.value)}/>

				<input value={inputs.studentObj.emailId} type="text" name="email" placeholder="Enter Email:"
                    onChange={(e) => setStudentInputs('emailId', e.target.value)}/>

                <input value={inputs.studentObj.role} type="text" name="role" placeholder="Enter Role:"
                    onChange={(e) => setStudentInputs('role', e.target.value)}/>

				<input value={inputs.studentObj.password} type="password" name="password" placeholder="Enter Password:"
                    onChange={(e) => setStudentInputs('password', e.target.value)}/>

                <button className='bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded' type="submit">Submit</button>
            </form> : null}
            <br />

			{loggedIn ?
            <form className='border-solid border-8 border-blue-300' onSubmit={(e) => formHandler.del(
                e,
                'sec/students',
                inputs.studentObj.studentId
            )}>
                <div className='bg-white'>(Student: DELETE)</div><br />
                <input value={inputs.studentObj.studentId} type="text" name="id" placeholder="Enter ID:"
                    onChange={(e) => setStudentInputs('studentId', e.target.value)}/>
                <button className='bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded' type="submit">Submit</button>
            </form> : null}
            <br />

			{loggedIn ?
            <form className='border-solid border-8 border-blue-300 space-y-2' onSubmit={(e) => formHandler.put(
                e,
                'sec/students',
                inputs.studentObj,
                inputs.studentObj.studentId
            )}>
                <div className='bg-white'>(Student: PUT)</div><br />
                <input value={inputs.studentObj.studentId} type="number" name="id" placeholder="Enter ID:"
                    onChange={(e) => setStudentInputs('studentId', Number(e.target.value))}/>

                <input value={inputs.studentObj.studentName} type="text" name="name" placeholder="Enter Name:"
                    onChange={(e) => setStudentInputs('studentName', e.target.value)}/>

				<input value={inputs.studentObj.emailId} type="text" name="email" placeholder="Enter Email:"
                    onChange={(e) => setStudentInputs('emailId', e.target.value)}/>

                <input value={inputs.studentObj.role} type="text" name="role" placeholder="Enter Role:"
                    onChange={(e) => setStudentInputs('role', e.target.value)}/>

				<input value={inputs.studentObj.password} type="password" name="password" placeholder="Enter Password:"
                    onChange={(e) => setStudentInputs('password', e.target.value)}/>

                <button className='bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded' type="submit">Submit</button>
            </form> : null}
            <br />
{/* Student Forms */}
{/* Course Forms */}
            {loggedIn ?
            <form className='border-solid border-8 border-blue-300 space-y-2' onSubmit={(e) => formHandler.post(
                e,
                'sec/courses',
                inputs.courseObj
            )}>
                <div className='bg-white'>(Course: POST)</div><br />
                <input value={inputs.courseObj.courseId} type="number" name="courseId" placeholder="Enter ID:"
                    onChange={(e) => setCourseInputs('courseId', Number(e.target.value))}/>

                <input value={inputs.courseObj.courseName} type="text" name="name" placeholder="Enter Name:"
                    onChange={(e) => setCourseInputs('courseName', e.target.value)}/>

				<input value={inputs.courseObj.id} type="text" name="id" placeholder="Enter Staff ID:"
                    onChange={(e) => setCourseInputs('id', e.target.value)}/>

                <button className='bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded' type="submit">Submit</button>
            </form> : null}
            <br />

			{loggedIn ?
            <form className='border-solid border-8 border-blue-300' onSubmit={(e) => formHandler.del(
                e,
                'sec/courses',
                inputs.courseObj.courseId
            )}>
                <div className='bg-white'>(Course: DELETE)</div><br />
                <input value={inputs.courseObj.courseId} type="number" name="courseId" placeholder="Enter ID:"
                    onChange={(e) => setCourseInputs('courseId', e.target.value)}/>
                <button className='bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded' type="submit">Submit</button>
            </form> : null}
            <br />

            {loggedIn ?
            <form className='border-solid border-8 border-blue-300 space-y-2' onSubmit={(e) => formHandler.put(
                e,
                'sec/courses',
                inputs.courseObj,
                inputs.courseObj.courseId
            )}>
                <div className='bg-white'>(Course: PUT)</div><br />
                <input value={inputs.courseObj.courseId} type="number" name="courseId" placeholder="Enter ID:"
                    onChange={(e) => setCourseInputs('courseId', Number(e.target.value))}/>

                <input value={inputs.courseObj.courseName} type="text" name="name" placeholder="Enter Name:"
                    onChange={(e) => setCourseInputs('courseName', e.target.value)}/>

				<input value={inputs.courseObj.id} type="number" name="id" placeholder="Enter Staff ID:"
                    onChange={(e) => setCourseInputs('id', Number(e.target.value))}/>

                <button className='bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded' type="submit">Submit</button>
            </form> : null}
            <br />
{/* Course Forms */}
{/* Enrollment Forms */}
            {loggedIn ?
            <form className='border-solid border-8 border-blue-300 space-y-2' onSubmit={(e) => formHandler.post(
                e,
                'sec/enrollments',
                inputs.enrolObj
            )}>
                <div className='bg-white'>(Enrollment: POST)</div><br />
                <input value={inputs.enrolObj.courseId} type="number" name="courseId" placeholder="Enter Course ID:"
                    onChange={(e) => setEnrolInputs('courseId', Number(e.target.value))}/>

                <input value={inputs.enrolObj.studentId} type="number" name="studentId" placeholder="Enter Student ID:"
                    onChange={(e) => setEnrolInputs('studentId', e.target.value)}/>

                <button className='bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded' type="submit">Submit</button>
            </form> : null}
            <br />

			{loggedIn ?
            <form className='border-solid border-8 border-blue-300' onSubmit={(e) => formHandler.del(
                e,
                'sec/enrollments',
                inputs.enrolObj.courseId
            )}>
                <div className='bg-white'>(Enrollment: DELETE)</div><br />
                <input value={inputs.studentObj.studentId} type="number" name="id" placeholder="Enter ID:"
                    onChange={(e) => setEnrolInputs('courseId', e.target.value)}/>
                <button className='bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded' type="submit">Submit</button>
            </form> : null}
            <br />

            {/* {loggedIn ?
            <form className='border-solid border-8 border-blue-300 space-y-2' onSubmit={(e) => formHandler.put(
                e,
                'sec/enrollments',
                inputs.enrolObj
            )}>
                <div className='bg-white'>(Enrollment: PUT)</div><br />
                <input value={inputs.enrolObj.courseId} type="number" name="courseId" placeholder="Enter Course ID:"
                    onChange={(e) => setEnrolInputs('courseId', Number(e.target.value))}/>

                <input value={inputs.enrolObj.studentId} type="number" name="studentId" placeholder="Enter Student ID:"
                    onChange={(e) => setEnrolInputs('studentId', e.target.value)}/>

                <button className='bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded' type="submit">Submit</button>
            </form> : null} */}
            <br />
{/* Enrollment Forms */}
{/* Staff Forms */}
            {loggedIn ?
            <form className='border-solid border-8 border-blue-300 space-y-2' onSubmit={(e) => formHandler.post(
                e,
                'sec/users',
                inputs.userObj
            )}>
                <div className='bg-white'>(Staff: POST)</div><br />
                <input value={inputs.userObj.id} type="number" name="id" placeholder="Enter ID:"
                    onChange={(e) => setUserInputs('studentId', Number(e.target.value))}/>

                <input value={inputs.userObj.userName} type="text" name="name" placeholder="Enter Name:"
                    onChange={(e) => setUserInputs('studentName', e.target.value)}/>

				<input value={inputs.userObj.emailId} type="text" name="email" placeholder="Enter Email:"
                    onChange={(e) => setUserInputs('emailId', e.target.value)}/>

                <input value={inputs.userObj.role} type="text" name="role" placeholder="Enter Role:"
                    onChange={(e) => setUserInputs('role', e.target.value)}/>

				<input value={inputs.userObj.password} type="password" name="password" placeholder="Enter Password:"
                    onChange={(e) => setUserInputs('password', e.target.value)}/>

                <button className='bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded' type="submit">Submit</button>
            </form> : null}
            <br />

			{loggedIn ?
            <form className='border-solid border-8 border-blue-300' onSubmit={(e) => formHandler.del(
                e,
                'sec/users',
                inputs.userObj.id
            )}>
                <div className='bg-white'>(Staff: DELETE)</div><br />
                <input value={inputs.userObj.id} type="text" name="id" placeholder="Enter ID:"
                    onChange={(e) => setUserInputs('id', e.target.value)}/>
                <button className='bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded' type="submit">Submit</button>
            </form> : null}
            <br />

            {loggedIn ?
            <form className='border-solid border-8 border-blue-300 space-y-2' onSubmit={(e) => formHandler.put(
                e,
                'sec/users',
                inputs.userObj,
                inputs.userObj.id
            )}>
                <div className='bg-white'>(Staff: PUT)</div><br />
                <input value={inputs.userObj.id} type="number" name="id" placeholder="Enter ID:"
                    onChange={(e) => setUserInputs('id', Number(e.target.value))}/>

                <input value={inputs.userObj.userName} type="text" name="name" placeholder="Enter Name:"
                    onChange={(e) => setUserInputs('userName', e.target.value)}/>

				<input value={inputs.userObj.emailId} type="text" name="email" placeholder="Enter Email:"
                    onChange={(e) => setUserInputs('emailId', e.target.value)}/>

                <input value={inputs.userObj.role} type="text" name="role" placeholder="Enter Role:"
                    onChange={(e) => setUserInputs('role', e.target.value)}/>

				<input value={inputs.userObj.password} type="password" name="password" placeholder="Enter Password:"
                    onChange={(e) => setUserInputs('password', e.target.value)}/>

                <button className='bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded' type="submit">Submit</button>
            </form> : null}
            <br />
{/* Staff Forms */}

		</div>
	)
}
