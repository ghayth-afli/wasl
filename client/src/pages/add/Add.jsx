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
import { toast } from "react-toastify";
import axios from "axios";

const Add = () => {
  const navigate = useNavigate();
  const [error, setError] = useState(null);
  const notifySuceess = () =>
    toast.success("🦄  Offer created !", {
      position: "top-right",
      autoClose: 5000,
      hideProgressBar: false,
      closeOnClick: true,
      pauseOnHover: true,
      draggable: true,
      progress: undefined,
      theme: "dark",
    });
  const notifyError = () =>
    toast.error("🦄 Offer not created — check it out again!", {
      position: "top-right",
      autoClose: 5000,
      hideProgressBar: false,
      closeOnClick: true,
      pauseOnHover: true,
      draggable: true,
      progress: undefined,
      theme: "dark",
    });

  const [formValues, setFormValues] = useState({
    title: "",
    description: "",
    depart: "",
    destination: "",
    date: "",
    time: "",
    capacity: "",
    price: "",
    image: "",
  });
  const [file, setFile] = useState(null);

  const uploadFile = async (file) => {
    if (!file) return null;
    const data = new FormData();
    data.append("file", file);
    data.append("upload_preset", "wassel");

    try {
      const response = await axios.post(
        "https://api.cloudinary.com/v1_1/dlxlpazb7/image/upload",
        data
      );
      const { url } = response.data;
      return url;
    } catch (error) {
      console.log(error);
    }
  };

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
    const imageUrl = await uploadFile(file);
    if (imageUrl !== null && imageUrl !== undefined) {
      setFormValues({
        ...formValues,
        image: imageUrl,
      });
    }

    setFormValues({
      time: formValues.time + ":00",
    });

    try {
      const response = await fetch(`${hosts.backend}/api/offers`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
        body: JSON.stringify({
          ...formValues,
          image: imageUrl,
          time: formValues.time + ":00",
        }),
      });
      if (!response.ok) {
        window.scrollTo(0, 0);
        throw new Error("HTTP error " + response.status);
      }

      await response.json();
      toast.success("👌 Offer created !", {
        position: "top-right",
        autoClose: 5000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
        theme: "dark",
      });
      window.scrollTo(0, 0);
    } catch (error) {
      toast.error("😰 Offer not created — check it out again!", {
        position: "top-right",
        autoClose: 10000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
        theme: "dark",
      });
      console.log("Fetch error:", error);
    }
  };

  return (
    <>
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
                {/* <FormControl fullWidth>
                  <InputLabel>Category</InputLabel>
                  <Select name="category" required onChange={handleChange}>
                    <MenuItem value="clothes">Clothes</MenuItem>
                    <MenuItem value="electronics">Electronics </MenuItem>
                    <MenuItem value="food">Food</MenuItem>
                    <MenuItem value="meds">Meds</MenuItem>
                  </Select>
                </FormControl> */}
                <InputLabel>Offer Images</InputLabel>
                <Button
                  component="label"
                  variant="outlined"
                  startIcon={<CloudUploadIcon />}
                >
                  Upload file
                  <input
                    type="file"
                    onChange={(e) => setFile(e.target.files[0])}
                  />
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
                  inputProps={{
                    min: new Date().toISOString().split("T")[0],
                  }}
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
