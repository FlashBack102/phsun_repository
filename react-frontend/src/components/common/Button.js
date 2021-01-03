import React from 'react';
import styled, { css } from 'styled-components';

const StyledButton = styled.button`
  border: none;
  border-radius: 4px;
  font-size: 1rem;
  font-weight: bold;
  padding: 0.25rem 1rem;
  color: white;
  outline: none;
  cursor: pointer;

  background: #40c057;
  &:hover {
    background: #69db7c;
  }

  ${(props) =>
    props.fullWidth &&
    css`
      padding-top: 0.75rem;
      padding-bottom: 0.75rem;
      width: 100%;
      font-size: 1.125rem;
    `}

  ${(props) =>
    props.cyan &&
    css`
      background: #74c0fc;
      &:hover {
        background: #a5d8ff;
      }
    `}
`;

const Button = (props) => <StyledButton {...props} />;

export default Button;
