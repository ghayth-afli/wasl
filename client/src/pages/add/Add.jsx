import React, { useState } from "react";
import "./Add.scss";
import {
  Button,
  TextField,
  Select,
  MenuItem,
  InputLabel,
  FormControl,
} from "@mui/material";
import CloudUploadIcon from "@mui/icons-material/CloudUpload";
import { styled } from "@mui/material/styles";
import { hosts } from "../../const";
import { Link, useNavigate } from "react-router-dom";
import Alert from "@mui/material/Alert";
import AlertTitle from "@mui/material/AlertTitle";
import Stack from "@mui/material/Stack";

const Add = () => {
  const navigate = useNavigate();
  const [error, setError] = useState(null);
  const VisuallyHiddenInput = styled("input")({
    clip: "rect(0 0 0 0)",
    clipPath: "inset(50%)",
    height: 1,
    overflow: "hidden",
    position: "absolute",
    bottom: 0,
    left: 0,
    whiteSpace: "nowrap",
    width: 1,
  });

  const [formValues, setFormValues] = useState({
    title: "",
    category: "",
    description: "",
    depart: "",
    destination: "",
    date: "",
    time: "",
    deliveryTime: "",
    capacity: "",
    price: "",
    image: "",
  });
  const [file, setFile] = useState(null);

  const handleFileChange = (event) => {
    console.log("event.target.files[0]", event.target.files[0]);
    setFile(event.target.files[0]);
  };

  const handleChange = (event) => {
    setFormValues({
      ...formValues,
      [event.target.name]: event.target.value,
    });
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    formValues["image"] = file?.name || "";
    formValues["time"] = new Date(formValues["time"]).getTime();

    try {
      const response = await fetch(`${hosts.backend}/api/offers`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
        body: JSON.stringify(formValues),
      });
      if (!response.ok) {
        setError(true);
        throw new Error("HTTP error " + response.status);
      }

      const data = await response.json();
      setError(false);
    } catch (error) {
      console.log("Fetch error:", error);
      setError(true);
    }
  };

  return (
    <>
      {error === true && (
        <Alert severity="error">
          <AlertTitle>Error</AlertTitle>
          Offer not created — <strong>check it out again!</strong>
        </Alert>
      )}
      {error === false && (
        <Alert severity="success">
          <AlertTitle>Success</AlertTitle>
          Offer created —{" "}
          <strong>
            {" "}
            <Link to="/mygigs">check it out!</Link>{" "}
          </strong>
        </Alert>
      )}
      <form onSubmit={handleSubmit}>
        <div className="add">
          <div className="container">
            <h1>Add New Gig</h1>
            <div className="sections">
              <div className="info">
                <TextField
                  name="title"
                  label="Title"
                  placeholder="e.g. I will do something I'm really good at"
                  fullWidth
                  required
                  onChange={handleChange}
                />
                <FormControl fullWidth>
                  <InputLabel>Category</InputLabel>
                  <Select name="category" required onChange={handleChange}>
                    <MenuItem value="clothes">Clothes</MenuItem>
                    <MenuItem value="electronics">Electronics </MenuItem>
                    <MenuItem value="food">Food</MenuItem>
                    <MenuItem value="meds">Meds</MenuItem>
                  </Select>
                </FormControl>
                <InputLabel>Offer Images</InputLabel>
                <Button
                  component="label"
                  variant="outlined"
                  startIcon={<CloudUploadIcon />}
                >
                  Upload file
                  <input type="file" onChange={handleFileChange} />
                </Button>
                <TextField
                  name="description"
                  label="Description"
                  placeholder="Brief descriptions to introduce your service to customers"
                  multiline
                  rows={16}
                  fullWidth
                  required
                  onChange={handleChange}
                />
                <Button variant="contained" type="submit">
                  Create
                </Button>
              </div>
              <div className="details">
                <TextField
                  name="depart"
                  label="Depart"
                  placeholder="e.g. Tunisie Carthage"
                  fullWidth
                  required
                  onChange={handleChange}
                />
                <TextField
                  name="destination"
                  label="Destination"
                  placeholder="e.g. Germany Berlin"
                  fullWidth
                  required
                  onChange={handleChange}
                />
                <TextField
                  name="date"
                  label="Date"
                  type="date"
                  fullWidth
                  required
                  onChange={handleChange}
                />
                <TextField
                  name="time"
                  label="Time"
                  type="time"
                  fullWidth
                  required
                  onChange={handleChange}
                />
                <TextField
                  name="deliveryTime"
                  label="Delivery Time (e.g. 3 days)"
                  type="number"
                  fullWidth
                  required
                  onChange={handleChange}
                />
                <TextField
                  name="capacity"
                  label="Capacity"
                  placeholder="e.g. 5 Kg"
                  fullWidth
                  required
                  onChange={handleChange}
                />
                <TextField
                  name="price"
                  label="Price"
                  type="number"
                  fullWidth
                  required
                  onChange={handleChange}
                />
              </div>
            </div>
          </div>
        </div>
      </form>
    </>
  );
};

export default Add;
