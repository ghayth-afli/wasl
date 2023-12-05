import React from "react";

import {
  Popover,
  PopoverTrigger,
  PopoverContent,
  PopoverBody,
  PopoverArrow,
  IconButton,
  Button,
  Stack,
  Flex,
} from "@chakra-ui/react";
import { BsThreeDotsVertical, BsChatSquareQuote } from "react-icons/bs";
import { RiShutDownLine, RiRestartLine, RiFileShredLine } from "react-icons/ri";
import axios from "axios";
import { toast } from "react-toastify";
import { hosts } from "../../const";

export default function OrderState({ role, requestId, offerId }) {
  console.log(requestId, offerId);
  const completeBtnHandler = () => {
    console.log("complete");
    axios
      .post(
        `${hosts.backend}/offers/${offerId}/requests/${requestId}/complete`,
        {},
        {
          headers: {
            "Content-Type": "Application/json ; charset=UTF-8",
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
        }
      )
      .then((res) => {
        toast.success(" Order completed !", {
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
        toast.error(" Order not completed !--check again later", {
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

  const cancelBtnHandler = () => {
    console.log("cancel");
    axios
      .put(
        `${hosts.backend}/offers/${offerId}/requests/${requestId}/cancel`,
        {},
        {
          headers: {
            "Content-Type": "Application/json ; charset=UTF-8",
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        }
      )
      .then((res) => {
        toast.success(" Order canceled !", {
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
        toast.error(" Order not canceled !--check again later", {
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
    /**
     */
    <Flex justifyContent="center" mt={4}>
      <Popover placement="bottom" isLazy>
        <PopoverTrigger>
          <IconButton
            aria-label="More server options"
            icon={<BsThreeDotsVertical />}
            variant="solid"
            w="fit-content"
          />
        </PopoverTrigger>
        <PopoverContent w="fit-content" _focus={{ boxShadow: "none" }}>
          <PopoverArrow />
          <PopoverBody>
            <Stack>
              {role == "traveler" && (
                <Button
                  w="194px"
                  variant="ghost"
                  rightIcon={<BsChatSquareQuote />}
                  justifyContent="space-between"
                  fontWeight="normal"
                  colorScheme="green"
                  fontSize="sm"
                  onClick={completeBtnHandler}
                >
                  Complete Request
                </Button>
              )}

              {role === "sender" && (
                <Button
                  w="194px"
                  variant="ghost"
                  rightIcon={<RiFileShredLine />}
                  justifyContent="space-between"
                  fontWeight="normal"
                  colorScheme="red"
                  fontSize="sm"
                  onClick={cancelBtnHandler}
                >
                  Cancel Request
                </Button>
              )}
            </Stack>
          </PopoverBody>
        </PopoverContent>
      </Popover>
    </Flex>
  );
}
