import SettingsIcon from '@mui/icons-material/Settings';
import { Chip, FormControl, Icon, InputLabel, MenuItem, Select } from '@mui/material';
import { ModalPayloadType, Skill, SkillModalAction } from '../../SkillTypes';

const Skillliste: React.FC<{
  skills: Skill[];
  dispatchModalState: React.Dispatch<SkillModalAction>;
  modalPayload: ModalPayloadType;
}> = (props) => {
  const skills = props.skills;

  const skillChips = (
    <div id="chips-container">
      {skills.map((skill) => (
        <Chip
        id="chip"
          key={skill.skillId}
          label={skill.skillName}
          onClick={() => {
            props.dispatchModalState({
              type: 'edit',
              skill,
              categories: props.modalPayload.categories,
            });
          }}
          onMouseDown={(event) => event.stopPropagation()}
          icon={<Icon>{<SettingsIcon fontSize="small"/>}</Icon>}
        />
      ))}
    </div>
  );

  return (
    <FormControl sx={{ minWidth: 600 }} size="small" id="skill-liste">
      <InputLabel id="skill-label" shrink>
        Skills
      </InputLabel>
      <Select
        displayEmpty
        size="small"
        label="Skills"
        labelId="skill-label"
        id="skill-liste"
        renderValue={() => skillChips}
      >
        <MenuItem onClick={() => props.dispatchModalState({ type: 'open', chosenCategory: props.modalPayload.chosenCategory })}>
          Neuen Skill erstellen
        </MenuItem>
      </Select>
    </FormControl>
  );
};

export default Skillliste;
