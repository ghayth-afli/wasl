import React from "react";
import "./Add.scss";
import { Button, TextField, Select, MenuItem, InputLabel, FormControl } from '@mui/material';
import CloudUploadIcon from '@mui/icons-material/CloudUpload';
import { styled } from '@mui/material/styles';

const Add = () => {
  const VisuallyHiddenInput = styled('input')({
    clip: 'rect(0 0 0 0)',
    clipPath: 'inset(50%)',
    height: 1,
    overflow: 'hidden',
    position: 'absolute',
    bottom: 0,
    left: 0,
    whiteSpace: 'nowrap',
    width: 1,
  });
  return (
   <>
    <div className="add">
      <div className="container">
        <h1>Add New Gig</h1>
        <div className="sections">
          <div className="info">
            <TextField label="Title" placeholder="e.g. I will do something I'm really good at" fullWidth />
            <FormControl fullWidth>
              <InputLabel>Category</InputLabel>
              <Select>
                <MenuItem value="design">Design</MenuItem>
                <MenuItem value="web">Web Development</MenuItem>
                <MenuItem value="animation">Animation</MenuItem>
                <MenuItem value="music">Music</MenuItem>
              </Select>
            </FormControl>
            <InputLabel>Images</InputLabel>
            <Button component="label" variant="outlined" startIcon={<CloudUploadIcon />} >
            Upload file
            <VisuallyHiddenInput type="file" />
          </Button>
            <input type="file"  />
            <TextField label="Description" placeholder="Brief descriptions to introduce your service to customers" multiline rows={16} fullWidth />
            <Button variant="contained">Create</Button>
          </div>
          <div className="details">
            <TextField label="Depart" placeholder="e.g. Tunisie Carthage" fullWidth />
            <TextField label="Destination" placeholder="e.g. Germany Berlin" fullWidth />
            <TextField label="Date" type="date" fullWidth />
            <TextField label="Time" type="time" fullWidth />
            <TextField label="Delivery Time (e.g. 3 days)" type="number" fullWidth />
            <TextField label="Capacity" placeholder="e.g. 5 Kg" fullWidth />
            <TextField label="Price" type="number" fullWidth />
          </div>
        </div>
      </div>
    </div>
   </>
  );
};

export default Add;
