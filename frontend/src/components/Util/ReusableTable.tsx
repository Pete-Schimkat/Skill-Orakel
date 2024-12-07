import {
  TableContainer,
  Paper,
  Table,
  TableHead,
  TableRow,
  TableCell,
  TableBody,
  Button,
  TableFooter,
  TablePagination,
} from '@mui/material';

import React from 'react';
import TablePaginationActions from './TablePagiantionActions';

export type TablePaginationActionsProps = {
  count: number;
  page: number;
  rowsPerPage: number;
  onPageChange: (
    event: React.MouseEvent<HTMLButtonElement>,
    newPage: number
  ) => void;
}
export type tableData = {
  id: number;
  row: string[];
}
export type TableProps = {
  columnHeaders: Column[];
  rows: tableData[];
}

export type Column = {
  id: number;
  label: string;
  minWidth?: number;
}

const ReusableTable = ({ columnHeaders, rows }: TableProps) => {
  const [page, setPage] = React.useState(0);
  const [rowsPerPage, setRowsPerPage] = React.useState(5);

  const handleChangePage = (
    _event: React.MouseEvent<HTMLButtonElement> | null,
    newPage: number
  ) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (
    event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
  ) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };
  return (
    <TableContainer component={Paper}>
      <Table sx={{ minWidth: 500 }} aria-label="custom pagination table">
        <TableHead>
          <TableRow>
            {columnHeaders.map((header: Column) => (
              <TableCell
                key={header.id}
                align="left"
                style={{ fontWeight: 'bold' }}
              >
                {header.label}
              </TableCell>
            ))}
          </TableRow>
        </TableHead>
        <TableBody>
          {rows.map((row) => (
            <TableRow key={row.id}>
              <TableCell>{row.id}</TableCell>
              {columnHeaders.map((header: Column) => (
                <TableCell key={header.id} align="left">
                  {row.row[header.id]}
                </TableCell>
              ))}
              <TableCell>
                <Button
                  style={{
                    fontWeight: 'bold',
                    fontSize: '1em',
                    backgroundColor: '#DDDDDD',
                    color: 'black',
                    padding: '0em',
                    margin: '0em',
                    maxWidth: '1.7em',
                    maxHeight: '1.7em',
                    minWidth: '1.7em',
                    minHeight: '1.7em',
                  }}
                >
                  ...
                </Button>
                {/* <Breadcrumbs maxItems={0} aria-label="breadcrumb"></Breadcrumbs> */}
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
      <TableFooter>
        <TableRow>
          <TablePagination
            rowsPerPageOptions={[5, 10, 25, { label: 'All', value: -1 }]}
            colSpan={3}
            count={rows.length}
            rowsPerPage={rowsPerPage}
            page={page}
            slotProps={{
              select: {
                inputProps: {
                  'aria-label': 'rows per page',
                },
                native: true,
              },
            }}
            onPageChange={handleChangePage}
            onRowsPerPageChange={handleChangeRowsPerPage}
            ActionsComponent={TablePaginationActions}
          />
        </TableRow>
      </TableFooter>
    </TableContainer>
  );
};

export default ReusableTable;
