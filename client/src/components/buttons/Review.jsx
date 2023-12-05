import React from "react";
import {
  useDisclosure,
  Button,
  Modal,
  ModalOverlay,
  ModalContent,
  ModalHeader,
  ModalFooter,
  ModalBody,
  ModalCloseButton,
  VStack,
  FormControl,
  FormLabel,
  Input,
  InputGroup,
  InputLeftElement,
  Textarea,
} from "@chakra-ui/react";
import { BsPerson } from "react-icons/bs";
import axios from "axios";
import { hosts } from "../../const.js";
import { toast } from "react-toastify";

function Review({ requestId }) {
  const { isOpen, onOpen, onClose } = useDisclosure();
  const [rating, setRating] = React.useState("");
  const [message, setMessage] = React.useState("");

  const submitReview = () => {
    axios
      .post(
        `${hosts.backend}/api/request/${requestId}/review`,
        {
          rating: rating,
          comment: message,
        },
        {
          headers: {
            "Content-Type": "Application/json ; charset=UTF-8",
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        }
      )
      .then((res) => {
        console.log(res.data);
        onClose();
        toast.success(" Review added!", {
          position: "top-right",
          autoClose: 5000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: true,
          draggable: true,
          progress: undefined,
          theme: "dark",
        });
      })
      .catch((err) => {
        console.log(err);
        onClose();
        toast.error(" Review not  added!", {
          position: "top-right",
          autoClose: 5000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: true,
          draggable: true,
          progress: undefined,
          theme: "dark",
        });
      });
  };
  return (
    <>
      <Button onClick={onOpen}>Add a review</Button>

      <Modal isOpen={isOpen} onClose={onClose}>
        <ModalOverlay />
        <ModalContent>
          <ModalHeader>Rate the service</ModalHeader>
          <ModalCloseButton />
          <ModalBody>
            <VStack spacing={5}>
              <FormControl isRequired>
                <FormLabel>Rating</FormLabel>

                <InputGroup>
                  <InputLeftElement>
                    <BsPerson />
                  </InputLeftElement>
                  <Input
                    min={1}
                    max={5}
                    type="number"
                    name="rating"
                    placeholder="Traveler rating"
                    onChange={(e) => setRating(e.target.value)}
                  />
                </InputGroup>
              </FormControl>

              <FormControl isRequired>
                <FormLabel>Comment</FormLabel>

                <Textarea
                  name="message"
                  placeholder="Your Message"
                  rows={6}
                  resize="none"
                  onChange={(e) => setMessage(e.target.value)}
                />
              </FormControl>
            </VStack>
          </ModalBody>

          <ModalFooter>
            <Button colorScheme="green" mr={3} onClick={submitReview}>
              Submit
            </Button>
            <Button variant="ghost" onClick={onClose}>
              Close
            </Button>
          </ModalFooter>
        </ModalContent>
      </Modal>
    </>
  );
}

export default Review;
