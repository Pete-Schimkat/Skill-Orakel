// import { Autocomplete, Box, Button, Modal, TextField } from '@mui/material';
// import React, { useState } from 'react';
// import { ProjectType, Skill } from '../../types/types';


// const MyModal: React.FC = ({}) => {
//   const [open, setOpen] = useState(false);

//   const [formData, setFormData] = useState<ProjectType>({
//     id: '130db3b6-8546-437c-a399-d65442033e09',
//     name: '',
//     customer: '',
//     description: '',
//   });

//   const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
//     const { name, value } = event.target;
//     setFormData((prevData) => ({ ...prevData, [name]: value }));
//   };

//   const handleSkillInputChange = (
//     _event: React.ChangeEvent<object>,
//     value: Skill[] | null
//   ) => {
//     setFormData((prevData) => ({ ...prevData, skills: value || [] }));
//   };

//   const handleSave = () => {
//     const newProject: ProjectType = {
//       ...formData,
//     };
//     console.log('New project:', newProject);
//   };

//   const handleOpen = () => {
//     setOpen(true);
//   };

//   const handleClose = () => {
//     setOpen(false);
//   };

//   return (
//     <div>
//       <Button onClick={handleOpen}>+</Button>
//       <Modal
//         open={open}
//         onClose={handleClose}
//         aria-labelledby="modal-modal-title"
//         aria-describedby="modal-modal-description"
//       >
//         <Box id="project-modal-box">
//           <h2> Projekt erstellen </h2>
//           <TextField
//             name="title"
//             onChange={handleInputChange}
//             label="Titel"
//             fullWidth
//             style={{ marginBottom: '1em' }}
//           ></TextField>
//           <TextField
//             name="customer"
//             onChange={handleInputChange}
//             label="Kunde"
//             fullWidth
//             style={{ marginBottom: '1em' }}
//           ></TextField>
//           <TextField
//             name="description"
//             onChange={handleInputChange}
//             label="Description"
//             fullWidth
//             style={{ marginBottom: '1em' }}
//           ></TextField>
//           <Autocomplete
//             renderInput={(params) => <TextField {...params} label="Skills" />}
//             options={skillOptions}
//             getOptionLabel={(skill) => skill.name}
//             onChange={handleSkillInputChange}
//             multiple
//             fullWidth
//             style={{ marginBottom: '1em' }}
//           ></Autocomplete>
//           <Button onClick={handleSave}>Speichern</Button>
//           <Button onClick={handleClose}>Abbrechen</Button>
//         </Box>
//       </Modal>
//     </div>
//   );
// };

// export default MyModal;
