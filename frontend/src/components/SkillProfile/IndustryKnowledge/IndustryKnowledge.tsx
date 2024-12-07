import { useEffect, useState } from 'react';
import { Industry } from '../../../types/types';

import AddIcon from '@mui/icons-material/Add';
import { Button, Checkbox, FormControlLabel, FormGroup } from '@mui/material';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import TextField from '@mui/material/TextField';
import { IndustryApi } from '../../../api/IndustryApi';

const label = { inputProps: { 'aria-label': 'Checkbox demo' } };

const PLACEHOLDER_ID = '73c7a4ac-6469-42c7-873c-4d650d9aaaa2';

export default function IndustryKnowledge({
  industryKnowledge,
  setIndustries
}: {
  industryKnowledge: Industry[];
  setIndustries: React.Dispatch<React.SetStateAction<Industry[]>>
}) {
  const [isAddingNewIndustry, setIsAddingNewIndustry] =
    useState<boolean>(false);
  const [fetchedIndustries, setFetchedIndustries] = useState<Industry[]>([]);

  useEffect(() => {
    const fetchIndustries = async () => {
      const industries = await IndustryApi.getAllIndustries();
      setFetchedIndustries(industries);
    }
    void fetchIndustries();
  }, []);

  const handleCheckboxTick = (clickedIndustry: Industry) => {
    setIndustries((knownIndustries) => {
      let newKnownIndustries = [...knownIndustries];
      if (!newKnownIndustries.includes(clickedIndustry)) {
        newKnownIndustries.push(clickedIndustry);
      } else {
        newKnownIndustries = newKnownIndustries.filter((element) => {
          return element.id != clickedIndustry.id;
        });
      }
      return newKnownIndustries;
    });
  };

  const onClickAddIndustry = () => {
    setIsAddingNewIndustry((previousState) => !previousState);
  };

  function addIndustryToAllIndustries(industryKnowledge: string) {
    //TODO: id should be a uuid
    fetchedIndustries.push({id: PLACEHOLDER_ID, name: industryKnowledge});
  }

  return (
    <section id="branchen-section">
      <h2>Branchenkenntnisse</h2>
      <FormGroup className="branchen-grid-container">
        {fetchedIndustries.map((industry) => {
          return (
            <FormControlLabel
              key={industry.id}
              control={
                <Checkbox
                  {...label}
                  checked={industryKnowledge.map(checkedIndustry => checkedIndustry.id).includes(industry.id)}
                />
              }
              onChange={() => handleCheckboxTick(industry)}
              label={industry.name}
            ></FormControlLabel>
          );
        })}
      </FormGroup>

      {isAddingNewIndustry ? null : (
        <Button
          className="branchen-button"
          variant="contained"
          aria-label="add"
          size="medium"
          onClick={() => onClickAddIndustry()}
          startIcon={<AddIcon />}
        >
          Add
        </Button>
      )}

      <Dialog
        open={isAddingNewIndustry}
        onClose={() => onClickAddIndustry()}
        PaperProps={{
          component: 'form',
          onSubmit: (event: React.FormEvent<HTMLFormElement>) => {
            event.preventDefault();
            const formData = new FormData(event.currentTarget);
            const formJson = Object.fromEntries(
              (formData).entries()
            );
            const industryKnowledge = formJson.industryKnowledge.toString();
            addIndustryToAllIndustries(industryKnowledge);
            onClickAddIndustry();
          },
        }}
      >
        <DialogTitle>Neue Branche</DialogTitle>
        <DialogContent>
          <TextField
            margin="dense"
            id="industryKnowledge"
            name="industryKnowledge"
            label="Branche"
            type="string"
            fullWidth
            variant="outlined"
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={() => onClickAddIndustry()}>Abbrechen</Button>
          <Button type="submit">Bestätigen</Button>
        </DialogActions>
      </Dialog>
    </section>
  );
}
