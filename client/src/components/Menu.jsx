import {
	// Utils
	useStore,
} from '../utils/barrel'

const options = [
	{
	  label: "Open",
	  value: "open",
	},
	{
	  label: "Closed",
	  value: "closed",
	},
]

export const Dropdown = () => {
	const open = useStore((state) => state.open)

	const handleChange = e => {
		console.log(e.target.value)
	}

	return (
		<div id="App">
			<div className="select-container">
				<select value="closed" onChange={handleChange}>
					{options.map((option) => (
					<option key={option.value} value={option.value}>{option.label}</option>
					))}
				</select>
			</div>
      	</div>
	)
}
