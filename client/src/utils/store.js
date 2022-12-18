import create from 'zustand'
import { devtools } from 'zustand/middleware'
import { immer } from 'zustand/middleware/immer'
import axios from 'axios'

// const urls = [
//   'http://localhost:4000/api/students',
//   'http://localhost:4000/api/users',
//   'http://localhost:4000/api/courses',
//   'http://localhost:4000/api/enrollments',
// ]

// export const get = async (url) => {
// 	try {
// 		let res = await axios.get(
// 			`${url}`,
// 		)
//     console.log('AXIOS')
// 		console.log(res.data)
//     // resolve(res.data.json())
// 		return res.data
// 	} catch (err) {
// 		console.error(err)
// 	}
// }

export const useStore = create(devtools(immer((set) => ({
  isOpen: false,
  open: () => set({ isOpen: true }),
  close: () => set({ isOpen: false }),
  showForm: false,
  toggleForm: () => set((state) => ({ showForm: !state.showForm })),
  items: {
    // students: get(urls[0]),
    // users: get(urls[1]),
    // courses: get(urls[2]),
    // enrollments: get(urls[3]),
    students: null,
    users: null,
    courses: null,
    enrollments: null,
  },
  // setItems: (items) => set(() => ({ items: items })),
  setItems: (selector, input) => set((state) => { state.items[selector] = input }),
  body: {},
  setBody: (body) => set(() => ({ body: body })),
  inputs: {
    // Usable for both POST & PUT
    studentObj: {
      studentId: 3,
      studentName: 'Student3',
      emailId: 'student3@email.com',
      role: 'ROLE_USER',
      password: '123'
    },
    courseObj: {
      courseId: 3,
      courseName: 'Course3',
      id: 2
    },
    enrolObj: {
      courseId: 2,
      studentId: 1
    },
    userObj: {
      id: 3,
      userName: 'Staff3',
      emailId: 'staff3@email.com',
      role: 'ROLE_ADMIN',
      password: '123'
    },
    authObj: {
      username: 'Staff1',
      password: '123'
    }
  },
  setStudentInputs: (selector, input) => set((state) => { state.inputs.studentObj[selector] = input }),
  setCourseInputs: (selector, input) => set((state) => { state.inputs.courseObj[selector] = input }),
  setEnrolInputs: (selector, input) => set((state) => { state.inputs.enrolObj[selector] = input }),
  setUserInputs: (selector, input) => set((state) => { state.inputs.userObj[selector] = input }),
  setAuthInputs: (selector, input) => set((state) => { state.inputs.authObj[selector] = input }),
  loggedIn: true,
  login: () => set({ loggedIn: true }),
  logout: () => set({ loggedIn: false }),
  // login: () => set((state) => ({ loggedIn: true })),
  // logout: () => set((state) => ({ loggedIn: false })),
  // toggleLogin: () => set((state) => ({ loggedIn: !state.loggedIn })),
  // setInputs: (inputs) => set(() => ({ inputs: inputs })),
  // toggleTodo: (todoId) =>
  //   set((state) => {
  //     state.todos[todoId].done = !state.todos[todoId].done
  // }),
  urls: [
    'http://localhost:4000/api/',
    'http://localhost:4000/',
    'https://codinghermit.net/api/',
  ],
  entities: [
    'students',
    'users',
    'courses',
    'enrollments',
  ]
}))))
