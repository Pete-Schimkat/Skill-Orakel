import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select, { SelectChangeEvent } from '@mui/material/Select';
import EditIcon from '@mui/icons-material/Edit';
import { Category } from '../../SkillTypes';

const CategoryMenu: React.FC<{
  skillCategories: Category[];
  handleChange: (event: SelectChangeEvent) => void;
  openCategoryModal: () => void;
  skillCategoryName: string;
}> = (props) => {
  return (
    <FormControl
      sx={{ minWidth: 240 }}
      id="category-select"
      margin="dense"
      size="small"
    >
      <InputLabel id="category-selcet-label" shrink>
        Category
      </InputLabel>
      <Select
        labelId="category-select-label"
        data-testid="category-select"
        id="select"
        value={props.skillCategoryName}
        label="Category"
        onChange={props.handleChange}
        size="small"
        renderValue={(value) => {
          return (
            <div
              style={{
                display: 'flex',
                flexDirection: 'row',
                justifyContent: 'space-between',
                gap: 1,
              }}
            >
              {value}
              <EditIcon
                onMouseDown={(event) => event.stopPropagation()}
                onClick={props.openCategoryModal}
              />
            </div>
          );
        }}
      >
        {props.skillCategories.map((category) => (
          <MenuItem
            data-testid={'menuitem-1'}
            key={category.categoryId}
            value={category.categoryName}
          >
            {category.categoryName}
          </MenuItem>
        ))}
      </Select>
    </FormControl>
  );
};

export default CategoryMenu;
