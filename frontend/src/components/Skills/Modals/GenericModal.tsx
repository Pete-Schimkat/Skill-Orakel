import { Modal, Box, TextField, Button, Autocomplete } from '@mui/material';
import { GenericProps } from '../SkillTypes';

const muiStyles = { bgcolor: 'background.paper', boxShadow: 24, p: 4 };

const GenericModal = <T,>({
  data,
  preChosen: selection,
  isModalOpen,
  chosenElement: ausgewaehltesElement,
  isEditing: isEditing,
  getOptionLabel,
  changeOption,
  changeText,
  handleSave,
  handleDelete,
  handleClear,
}: GenericProps<T>) => {
  return (
    <Modal open={isModalOpen} disablePortal data-testid="modal">
      <Box id="skill-modal" data-testid="skill-modal" sx={muiStyles}>
        <h2>Skill {isEditing ? 'bearbeiten' : 'erstellen'}</h2>
        <TextField
          id="modal-textfield"
          name="Name"
          label="Name"
          role="form"
          value={ausgewaehltesElement}
          onChange={(event) => changeText(event.target.value)}
        />

        <Autocomplete
          data-testid="modal-autocomplete"
          multiple
          options={data}
          disableCloseOnSelect
          value={selection}
          onChange={(_event, newValue) => {
            changeOption([...newValue]);
          }}
          isOptionEqualToValue={(option, value) => {
            return getOptionLabel(option) === getOptionLabel(value);
          }}
          getOptionLabel={getOptionLabel}
          renderInput={(params) => (
            <TextField {...params} variant="standard" label="Category" />
          )}
        />

        <section data-testid="buttons" id="buttons">
          <Button variant="contained" role="button" onClick={handleSave}>
            {isEditing && 'Änderungen '}Speichern
          </Button>
          <Button variant="outlined" role="button" onClick={handleClear}>
            Abbrechen
          </Button>
          {isEditing && (
            <Button role="button" variant="outlined" onClick={handleDelete}>
              Löschen
            </Button>
          )}
        </section>
      </Box>
    </Modal>
  );
};

export default GenericModal;
